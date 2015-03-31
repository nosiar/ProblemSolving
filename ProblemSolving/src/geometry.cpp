#include <cmath>
#include <utility>
#include <functional>
#include <algorithm>
#include <vector>

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
    point operator/(double d) const
    {
        return{ x/d, y/d };
    }
};
using vec = point;

int ccw(point p0, point p1, point p2)
{
    double c = (p1 - p0).cross(p2 - p0);
    if (abs(c) < EPS) return 0;
    if (c > 0) return -1;
    return 1;
}

bool intersect(point p0, point p1, point p2, point p3)
{
    return ccw(p0, p1, p2)*ccw(p0, p1, p3) <= 0 && ccw(p2, p3, p0)*ccw(p2, p3, p1) <= 0;
}

std::vector<point> graham(std::vector<point>& points, bool include_collinear = false)
{    
    std::sort(points.begin(), points.end());

    /**********************************************************************************/
    // .. 그냥 >,< 나 >=,<= 하자 (..)
    std::function<bool(int)> is_concave_upper;
    std::function<bool(int)> is_concave_lower;
    if (include_collinear)
    {
        is_concave_upper = std::bind(std::greater<int>(), std::placeholders::_1, 0);
        is_concave_lower = std::bind(std::less<int>(), std::placeholders::_1, 0);
    }
    else
    {
        is_concave_upper = std::bind(std::greater_equal<int>(), std::placeholders::_1, 0);
        is_concave_lower = std::bind(std::less_equal<int>(), std::placeholders::_1, 0);
    }
    /**********************************************************************************/

    std::vector<point> upper, lower;
    for (auto p : points)
    {
        while (upper.size() > 1 && is_concave_upper(ccw(*++upper.rbegin(), *upper.rbegin(), p)))
            upper.pop_back();
        while (lower.size() > 1 && is_concave_lower(ccw(*++lower.rbegin(), *lower.rbegin(), p)))
            lower.pop_back();
        upper.push_back(p);
        lower.push_back(p);
    }
    lower.insert(lower.end(), ++upper.rbegin(), --upper.rend());
    return lower;
}