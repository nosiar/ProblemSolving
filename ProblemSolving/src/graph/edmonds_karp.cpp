#include <vector>
#include <queue>

template<size_t v>
int edmonds_karp(int source, int sink, int(&capacity)[v][v])
{
    int flow[v][v];
    memset(flow, 0, sizeof(flow));

    int net_flow = 0;

    while (true)
    {
        std::vector<int> parent(v, -1);
        std::queue<int> q;
        q.push(source);
        while (!q.empty())
        {
            int here = q.front(); q.pop();

            for (int there = 0; there < v; ++there)
            {
                if (capacity[here][there] > flow[here][there] && parent[there] == -1)
                {
                    q.push(there);
                    parent[there] = here;
                }
            }
        }

        if (parent[sink] == -1)
            break;

        int additional_flow = 987654321;
        for (int cur = sink; cur != source; cur = parent[cur])
            additional_flow = min(additional_flow, capacity[parent[cur]][cur] - flow[parent[cur]][cur]);
        for (int cur = sink; cur != source; cur = parent[cur])
        {
            flow[parent[cur]][cur] += additional_flow;
            flow[cur][parent[cur]] -= additional_flow;
        }
        net_flow += additional_flow;
    }

    return net_flow;
}

template<size_t v_a, size_t v_b>
bool dfs(int a, bool(&adj)[v_a][v_b], std::vector<int>&match_a, std::vector<int>&match_b, std::vector<bool>&visited)
{
    if (visited[a]) return false;
    visited[a] = true;

    for (int b = 0; b < v_b; ++b)
    {
        if (adj[a][b])
        {
            if (match_b[b] == -1 || dfs(match_b[b], adj, match_a, match_b, visited))
            {
                match_a[a] = b;
                match_b[b] = a;
                return true;
            }
        }
    }
    return false;
}

template<size_t v_a, size_t v_b>
int bipartite_matching(bool(&adj)[v_a][v_b])
{
    std::vector<int> match_a(v_a, -1);
    std::vector<int> match_b(v_b, -1);

    int size = 0;
    for (int start = 0; start < v_a; ++start)
    {
        std::vector<bool>visited(v_a);
        if (dfs(start, adj, match_a, match_b, visited))
            size++;
    }
    return size;
}