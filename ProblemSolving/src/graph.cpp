#include <vector>
#include <queue>
#include <functional>

/************************************************************************/
/*  edges[from] : vector of {weight, to}                                    */
/*  v : number of vertex                                                */
/*  s : source                                                          */
/*  d : destination                                                     */
/************************************************************************/
template<class T>
T dijkstra(std::vector<std::pair<T, int>> *edges, int v, int s, int d)
{
    std::priority_queue<std::pair<T, int>,
                        std::vector<pair<T, int>>, 
                        std::greater<pair<T, int>>> q;
    q.push({ 0.0, s });

    std::vector<T> dist(v, 987654321.0);
    dist[s] = 0;

    while (!q.empty())
    {
        auto here = q.top(); q.pop();

        if (here.first > dist[here.second])
            continue;

        for (auto edge : edges[here.second])
        {
            T new_dist = dist[here.second] + edge.first;
            if (dist[edge.second] > new_dist)
            {
                dist[edge.second] = new_dist;
                q.push({ new_dist, edge.second });
            }
        }
    }

    return dist[d];
}

template<class T, size_t v>
void floyd_warshall(std::vector<std::pair<T, int>> *edges, T(&dist)[v][v])
{
    for (int i = 0; i < v; ++i)
    {
        for (int j = 0; j < v; ++j)
        {
            if (j == i)
                dist[i][j] = 0;
            else
                dist[i][j] = 987654321;
        }
        for (auto edge : edges[i])
            dist[i][edge.second] = edge.first;
    }

    for (int k = 0; k < v; ++k)
    {
        for (int i = 0; i < v; ++i)
        {
            for (int j = 0; j < v; ++j)
            {
                T new_dist = dist[i][k] + dist[k][j];
                if (dist[i][j] > new_dist)
                    dist[i][j] = new_dist;
            }
        }
    }
}