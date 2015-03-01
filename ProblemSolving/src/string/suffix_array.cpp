#include <string>
#include <vector>
#include <algorithm>
#include <utility>

// Manber & Myers
std::vector<int> suffix_array(const std::string& str)
{
    int n = str.length();

    std::vector<int> group(n + 1);
    group[n] = -1;
    for (int i = 0; i < n; ++i) group[i] = str[i];
    int t = 1;    
    
    std::vector<int> suffix_list(n);
    for (int i = 0; i < n; ++i) suffix_list[i] = i;
    while (t < n)
    {
        auto compare = [t, &group](int a, int b)
        {
            if (group[a] == group[b])
                return group[a + t] < group[b + t];
            return group[a] < group[b];
        };
        std::sort(suffix_list.begin(), suffix_list.end(), compare);
        
        t *= 2;
        if (t >= n) break;

        std::vector<int> next_group(n + 1);
        next_group[n] = -1;
        next_group[suffix_list[0]] = 0;
        for (int i = 1; i < n; ++i)
        {
            if (compare(suffix_list[i - 1], suffix_list[i]))
                next_group[suffix_list[i]] = next_group[suffix_list[i - 1]] + 1;
            else
                next_group[suffix_list[i]] = next_group[suffix_list[i - 1]];
        }
        group = std::move(next_group); // I'm not sure if std::move is good here.. Maybe it causes unnecessary memory allocations?
    }
    return suffix_list;
}