#include <vector>
#include <algorithm>

struct bigint
{
    int base;
    std::vector<int> num;
    bigint(const std::vector<int>& num_, int base_ = 10) : num(num_), base(base_) {}
    bigint(std::vector<int>&& num_, int base_ = 10) : num(std::move(num_)), base(base_) {}

    std::vector<int> add(const std::vector<int>& a, const std::vector<int>& b) const
    {
        std::vector<int> c(1 + std::max(a.size(), b.size()));

        auto a_it = a.begin();
        auto b_it = b.begin();
        auto c_it = c.begin();

        while (a_it != a.end() && b_it != b.end())
            *c_it++ = *a_it++ + *b_it++;
        while (a_it != a.end())
            *c_it++ = *a_it++;
        while (b_it != b.end())
            *c_it++ = *b_it++;

        normalize(c);

        return c;
    }

    // assume that a is larger than b
    std::vector<int> sub(const std::vector<int>& a, const std::vector<int>& b) const
    {
        std::vector<int> c(a.size());

        auto a_it = a.begin();
        auto b_it = b.begin();
        auto c_it = c.begin();

        while (b_it != b.end())
            *c_it++ = *a_it++ - *b_it++;
        while (a_it != a.end())
            *c_it++ = *a_it++;

        normalize(c);

        return c;
    }

    std::vector<int> mul_naive(const std::vector<int>& a, const std::vector<int>& b) const
    {
        std::vector<int> c(a.size() + b.size());
        for (int i = 0; i < a.size(); ++i)
            for (int j = 0; j < b.size(); ++j)
                c[i + j] += a[i] * b[j];

        normalize(c);

        return c;
    }

    std::vector<int> mul(const std::vector<int>& a, const std::vector<int>& b) const
    {
        if (a.size() == 0 || b.size() == 0) return std::vector<int>();

        if (a.size() + b.size() < 50) return mul_naive(a, b);

        std::vector<int> c(a.size() + b.size());

        size_t half = std::max(a.size(), b.size()) / 2;
        std::vector<int> a0(a.begin(), a.begin() + std::min(half, a.size()));
        std::vector<int> a1(a.begin() + std::min(half, a.size()), a.end());
        std::vector<int> b0(b.begin(), b.begin() + std::min(half, b.size()));
        std::vector<int> b1(b.begin() + std::min(half, b.size()), b.end());

        auto z0 = mul(a0, b0);
        auto z1 = mul(a1, b1);
        auto z2 = sub(sub(mul(add(a0, a1), add(b0, b1)), z0), z1);
        
        auto z0_it = z0.begin();
        auto z1_it = z1.begin();
        auto z2_it = z2.begin();

        auto c_it = c.begin();
        while (z0_it != z0.end())
            *c_it++ += *z0_it++;

        c_it = c.begin() + half;
        while (z1_it != z1.end())
            *c_it++ += *z1_it++;

        c_it = c.begin() + 2 * half;
        while (z2_it != z2.end())
            *c_it++ += *z2_it++;

        normalize(c);

        return c;
    }

    bigint operator+(const bigint& other) const
    {
        return bigint(add(num, other.num), base);
    }

    // assume that this is larger than other
    bigint operator-(const bigint& other) const
    {
        return bigint(sub(num, other.num), base);
    }

    bigint operator*(const bigint& other) const
    {
        return bigint(mul(num, other.num), base);
    }

    int& operator[](int i)
    {
        return num[i];
    }

    void normalize(std::vector<int>& a) const
    {
        for (int i = 0; i < a.size(); ++i)
        {
            if (a[i] < 0)
            {
                int borrow = (std::abs(a[i]) + base - 1) / base;

                a[i] += borrow * base;
                a[i + 1] -= borrow;
            }
            else
            {
                a[i + 1] += a[i] / base;
                a[i] %= base;
            }
        }
        while (!a.empty() && a.back() == 0)
            a.pop_back();
    }
};