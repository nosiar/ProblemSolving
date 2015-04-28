#include <vector>
#include <stack>
#include <algorithm>

struct tarjan
{
    int n;
    std::vector<std::vector<int>>& adj;
    std::vector<int> id;
    std::vector<int> scc_id;
    int next_id;
    int next_scc_id;
    std::stack<int> st;

    tarjan(std::vector<std::vector<int>>& adj_) : adj(adj_), n(adj_.size()), id(n, -1), scc_id(n, -1), next_id(0), next_scc_id(0)
    {
    }

    int scc(int i)
    {
        st.push(i);

        int ret = id[i] = next_id++;

        for (int j : adj[i])
        {
            if (id[j] == -1)
                ret = std::min(ret, scc(j));
            else if (scc_id[j] == -1) // 역방향, 교차 둘다 봐야함
                ret = std::min(ret, id[j]);
        }

        if (id[i] == ret)
        {
            while (true)
            {
                int x = st.top(); st.pop();
                scc_id[x] = next_scc_id;

                if (x == i)
                    break;
            }
            ++next_scc_id;
        }

        return ret;
    }

    void do_tarjan()
    {
        for (int i = 0; i < n; ++i)
            if (id[i] == -1)
                scc(i);
    }

    const std::vector<int>& operator()()
    {
        if (scc_id[0] == -1)
            do_tarjan();

        return scc_id;
    }
};