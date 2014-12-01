#include <vector>
#include <queue>
#include <functional>

/************************************************************************/
/*  e[from] : vector of {weight, to}                                    */
/*  n : number of vertex                                                */
/*  s : source                                                          */
/*  d : destination                                                     */
/************************************************************************/
template<class T>
T dijkstra(std::vector<std::pair<T, int>> *e, int n, int s, int d)
{
    std::priority_queue<std::pair<T, int>,
                        std::vector<pair<T, int>>, 
                        std::greater<pair<T, int>>> q;
    q.push({ 0.0, s });

    std::vector<T> dist(n, 987654321.0);
    dist[s] = 0;

    while (!q.empty())
    {
        auto cur = q.top(); q.pop();

        if (cur.first > dist[cur.second])
            continue;

        for (auto edge : e[cur.second])
        {
            T new_dist = dist[cur.second] + edge.first;
            if (dist[edge.second] > new_dist)
            {
                dist[edge.second] = new_dist;
                q.push({ new_dist, edge.second });
            }
        }
    }

    return dist[d];
}