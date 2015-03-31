#include <cmath>
#include <utility>

#define PI 3.14159265358979323846
#define EPS 1e-9

struct point
{
    double x, y;
    bool operator<(const point& rhs) const
    {
        if (abs(x - rhs.x) < EPS)
            return y + EPS < rhs.y;
        return x < rhs.x;
    }
    bool operator>(const point& rhs) const
    {
        return rhs < *this;
    }
    bool operator<=(const point& rhs) const
    {
        return !(*this > rhs);
    }
    bool operator>=(const point& rhs) const
    {
        return !(*this < rhs);
    }
    bool operator==(const point& rhs) const
    {
        return abs(x - rhs.x) < EPS && abs(y - rhs.y) < EPS;
    }
    bool operator!=(const point& rhs) const
    {
        return !(*this == rhs);
    }
    double length() const
    {
        return std::sqrt(x*x + y*y);
    }
    point normalize() const
    {
        double len = length();
        return{ x / len, y / len };
    }
    double dot(const point& rhs) const
    {
        return x*rhs.x + y*rhs.y;
    }
    double cross(const point& rhs) const
    {
        return x*rhs.y - rhs.x*y;
    }
    point operator+(const point& rhs) const
    {
        return{ x + rhs.x, y + rhs.y };
    }
    point operator-(const point& rhs) const
    {
        return{ x - rhs.x, y - rhs.y };
    }
    point operator*(double d) const
    {
        return{ d*x, d*y };
    }
};
using vec = point;

