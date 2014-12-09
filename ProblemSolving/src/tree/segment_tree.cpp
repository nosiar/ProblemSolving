#include <vector>
#include <functional>
#include <limits>
#include <algorithm>

template<typename T, typename S>
struct segment_tree
{
    int n;
    std::vector<S> nodes;

    std::function<S(S,S)> merge;
    std::function<S(T)> convert;

    S ignore;

    segment_tree(const T* arr, int n_, std::function<S(S, S)> merge_, std::function<S(T)> convert_, S ignore_)
        : n(n_), merge(merge_), convert(convert_), ignore(ignore_)
    {
        int size = 2;
        if (n > 1)
        {
            double d = n - 1;
            size = 1 << ((((int*)&d)[1] >> 20) - 1021);
        }
        nodes.resize(size);

        init(arr, 0, n - 1, 1);
    };

    S init(const T* arr, int left, int right, int i)
    {
        if (left == right)
            return nodes[i] = convert(arr[left]);
     
        int mid = (left + right) / 2;
        S left_val = init(arr, left, mid, i * 2);
        S right_val = init(arr, mid + 1, right, i * 2 + 1);

        return nodes[i] = merge(left_val, right_val);
    }

    S query(int target_left, int target_right, int left, int right, int i)
    {
        if (target_left <= left && right <= target_right)
            return nodes[i];

        if (target_right < left || right < target_left)
            return ignore;

        int mid = (left + right) / 2;
        S left_val = query(target_left, target_right, left, mid, i * 2);
        S right_val = query(target_left, target_right, mid + 1, right, i * 2 + 1);

        return merge(left_val, right_val);
    }

    S query(int target_left, int target_right)
    {
        return query(target_left, target_right, 0, n - 1, 1);
    }

    S update(int k, T new_value, int left, int right, int i)
    {
        if (left == right && right == k)
            return nodes[i] = convert(new_value);

        if (left <= k && k <= right)
        {
            int mid = (left + right) / 2;
            S left_val = update(k, new_value, left, mid, i * 2);
            S right_val = update(k, new_value, mid + 1, right, i * 2 + 1);

            return nodes[i] = merge(left_val, right_val);
        }

        return nodes[i];
    }
    
    void update(int k, T new_value)
    {
        update(k, new_value, 0, n - 1, 1);
    }
};

struct min_query : segment_tree < int, int >
{
    min_query(int* arr, int n_)
        : segment_tree(arr, n_, [](int a, int b) { return std::min(a, b); },
                       [](int a) { return a; },
                       std::numeric_limits<int>::max()) {}
};