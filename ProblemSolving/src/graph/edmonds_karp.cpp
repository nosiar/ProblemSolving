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