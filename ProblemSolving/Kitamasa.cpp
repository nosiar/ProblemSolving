#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>

/**
* solve k-step fibonacci with Kitamasa algorithm
* n >= k : a_n = a_(n-1) + a_(n-2) + ... + a_(n-k)
* n < k : a_n = 1
*/
namespace kitamasa
{
    std::vector<std::vector<long long>> coef_base;

    int k;
    long long mod = 1000000007;

    std::vector<long long> kitamasa_sub(int n)
    {
        if (n < 2 * k - 1)
            return coef_base[n];

        std::vector<long long> coef(k);

        if (n & 1)
        {
            auto coef_sub = kitamasa_sub(n - 1);
            for (int i = 1; i < k; ++i)
                coef[i] = coef_sub[i - 1];
            for (int i = 0; i < k; ++i)
                coef[i] = (coef[i] + coef_sub[k - 1] * coef_base[k][i]) % mod;

        }
        else
        {
            auto coef_sub = kitamasa_sub(n >> 1);
            std::vector<long long> temp(2 * k - 1);
            for (int i = 0; i < k; ++i)
                for (int j = 0; j < k; ++j)
                    temp[i + j] = (temp[i + j] + coef_sub[i] * coef_sub[j]) % mod;

            std::copy(temp.begin(), temp.begin() + k, coef.begin());
            for (int i = k; i < 2 * k - 1; ++i)
                for (int j = 0; j < k; ++j)
                    coef[j] = (coef[j] + temp[i] * coef_base[i][j]) % mod;
        }

        return coef;
    }

    int kitamasa(int n, int k_)
    {
        k = k_;

        if (n < k) return 1;

        // a_n = ¥Òcoef[n][i] * a_i
        coef_base.resize(2 * k - 1);

        // [0,k)
        for (int i = 0; i < k; ++i)
        {
            coef_base[i].resize(k, 0);
            coef_base[i][i] = 1;
        }

        // [k,k+1)
        coef_base[k].resize(k, 1);

        // [k+1, 2k-1)
        for (int i = k + 1; i < 2 * k - 1; ++i)
        {
            coef_base[i].resize(k, 0);
            for (int j = 0; j < k; ++j)
                coef_base[i][j] = (2 * coef_base[i - 1][j] - coef_base[i - k - 1][j] + mod) % mod;
        }

        auto coef_final = kitamasa_sub(n);
        return std::accumulate(coef_final.begin(), coef_final.end(), 0ll) % mod;
    }
}