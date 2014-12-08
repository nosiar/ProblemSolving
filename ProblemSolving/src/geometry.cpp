#include <cmath>
#include <utility>

#define PI 3.14159265358979323846
#define EPS 1e-9

#define x first
#define y second

typedef std::pair<double, double> point;
typedef std::pair<double, double> vec;

double length(vec v)
{
    return std::sqrt(v.x*v.x + v.y*v.y);
}

vec normalize(vec v)
{
    double len = length(v);
    return{ v.x / len, v.y / len };
}

double dot(vec v0, vec v1)
{
    return v0.x*v1.x + v0.y*v1.y;
}

double cross(vec v0, vec v1)
{
    return v0.x*v1.y - v1.x*v0.y;
}

vec operator+(vec v0, vec v1)
{
    return{ v0.x + v1.x, v0.y + v1.y };
}

vec operator-(vec v0, vec v1)
{
    return{ v0.x - v1.x, v0.y - v1.y };
}

vec operator*(double d, vec v)
{
    return{ d*v.x, d*v.y };
}

bool operator==(vec v0, vec v1)
{
    return abs(v0.x - v1.x) < EPS && abs(v0.y - v1.y) < EPS;
}