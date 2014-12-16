#include <vector>

struct disjoint_set
{
    std::vector<int> parent;
    std::vector<int> rank;
    std::vector<int> size;

    disjoint_set(int n) : parent(n), rank(n), size(n)
    {
        for (int i = 0; i < n; ++i)
        {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    int find(int a)
    {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    void yunion(int a, int b)
    {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (rank[a] < rank[b])
        {
            parent[a] = b;
            size[b] += size[a];
        }
        else if (rank[a] > rank[b])
        {
            parent[b] = a;
            size[a] += size[b];
        }
        else
        {
            parent[a] = b;
            rank[b]++;
            size[b] += size[a];
        }
    }
};