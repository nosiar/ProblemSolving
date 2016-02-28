#include <vector>
#include <complex>
#include <cmath>
#include <algorithm>

using complex = std::complex<double>;
using polynomial = std::vector<int>;
using complex_vector = std::vector<complex>;

const double PI = std::acos(-1);

unsigned int pow2ceil(unsigned int n)
{
    --n;
    n |= n >> 1;
    n |= n >> 2;
    n |= n >> 4;
    n |= n >> 8;
    n |= n >> 16;
    return n + 1;
}

complex_vector fft(const complex_vector& p, complex w)
{
    auto n = p.size();
    if (n == 1) return p;

    complex_vector even(n / 2);
    complex_vector odd(n / 2);

    bool is_even = false;
    std::partition_copy(p.begin(), p.end(), even.begin(), odd.begin(),
        [&is_even](const complex&){ return is_even = !is_even; });

    complex_vector even_f = fft(even, w*w);
    complex_vector odd_f = fft(odd, w*w);

    complex_vector r(n);
    complex power_w = 1;
    for (unsigned int i = 0; i < n / 2; ++i)
    {
        auto o = power_w*odd_f[i];
        r[i] = even_f[i] + o;
        r[i + n / 2] = even_f[i] - o;
        power_w *= w;
    }
    return r;
}

polynomial operator*(const polynomial& a, const polynomial& b)
{
    auto n = 2 * pow2ceil(std::max(a.size(), b.size()));

    complex_vector ac(a.begin(), a.end());
    complex_vector bc(b.begin(), b.end());

    ac.resize(n, 0);
    bc.resize(n, 0);

    auto w = std::polar(1.0, 2 * PI / n);

    ac = fft(ac, w);
    bc = fft(bc, w);

    for (unsigned int i = 0; i < n; ++i)
        ac[i] *= bc[i];

    ac = fft(ac, std::polar(1.0, -2 * PI / n));

    while (!ac.empty() && fabs(ac.back().real()) < 1e-9)
        ac.pop_back();

    polynomial r;
    std::transform(ac.begin(), ac.end(), std::back_inserter(r),
        [n](const complex& c){ return round(c.real() / n); });

    return r;
}
