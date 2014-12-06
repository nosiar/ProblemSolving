#include <vector>
#include <tuple>

int gcd(int a, int b)
{
    if (b == 0) return a;
    return gcd(b, a % b);
}

/* return { d, x, y } where gcd(a,b) = d = ax + by */
std::tuple<int, int, int> extended_gcd(int a, int b)
{
    if (b == 0)
        return std::tuple<int, int, int> { a, 1, 0 };

    auto r = extended_gcd(b, a % b);

    int d = std::get<0>(r);
    int x = std::get<1>(r);
    int y = std::get<2>(r);

    return std::tuple<int, int, int>{ d, y, x - a/b*y };
}

/* a has a (unique) multiplicative inverse iif gcd(a,n)=1 */
int inverse(int a, int n)
{
    auto r = extended_gcd(a, n);
    if (std::get<0>(r) != 1) return -1;
    return std::get<1>(r);
}

/*
 * min_factor[i] == i -> i is prime
 * factor[i] : number of divisors
 * phi[i] : Euler's totient function
 *          if gcd(a, m) = 1 then a^phi(m) = 1 (mod m)
 *          therefore a^(phi(m)-1) = a^-1
 *          if m is prime, a^(m-2) = a^-1
 */
void eratosthenes(int n)
{
    std::vector<int> min_factor(n + 1);
    std::vector<int> factor(n + 1);
    std::vector<int> phi(n + 1);

    for (int i = 2; i <= n; ++i)
        min_factor[i] = i;

    int bound = sqrt(n);

    phi[1] = 1;
    factor[1] = 1;
    for (int i = 2; i <= n; ++i)
    {
        if (min_factor[i] == i)
        {
            phi[i] = i - 1;
            factor[i] = 2;
            if (i <= bound)
                for (int j = i*i; j <= n; j += i)
                    if (min_factor[j] == j)
                        min_factor[j] = i;
        }
        else
        {
            int pre_i = i / min_factor[i];

            if (min_factor[i] == min_factor[pre_i])
                phi[i] = phi[pre_i] * min_factor[i];
            else
                phi[i] = phi[pre_i] * (min_factor[i] - 1);

            int temp = i;
            int c = 0;
            while (temp % min_factor[i] == 0)
            {
                temp /= min_factor[i];
                c++;
            }
            factor[i] = factor[i / min_factor[i]] * (c + 1) / c;
        }
    }
}