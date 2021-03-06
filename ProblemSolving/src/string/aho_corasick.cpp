﻿#include <iostream>
#include <string>
#include <queue>

struct trie
{
    struct node
    {
        int children[26]{};
        int& child(char x) { return children[x - 'a']; }
        int suffix{};
        int suffix_dist{};
        bool in_dict{};
    };
    node n[400];
    const int root = 0;
    int new_n = 1;

    void add(char* word)
    {
        int cur_node = root;
        char* cur_char = word;

        while (*cur_char)
        {
            if (!n[cur_node].child(*cur_char))
                n[cur_node].child(*cur_char) = new_n++;

            cur_node = n[cur_node].child(*cur_char);
            cur_char++;
        }
        n[cur_node].in_dict = true;
    }

    void link()
    {
        std::queue<int> q;

        for (int i = 0; i < 26; ++i)
        {
            if (n[root].children[i])
            {
                n[n[root].children[i]].suffix = root;
                n[n[root].children[i]].suffix_dist = 1;
                q.push(n[root].children[i]);
            }
        }

        while (!q.empty())
        {
            int cur_node = q.front(); q.pop();

            for (int i = 0; i < 26; ++i)
            {
                int s = n[cur_node].suffix;
                int s_dist = n[cur_node].suffix_dist;

                while (s && !n[s].children[i])
                {
                    s_dist += n[s].suffix_dist;
                    s = n[s].suffix;
                }

                // suffix가 root까지 올라갔는데도 못찾을 경우
                if (!n[s].children[i])
                    s_dist++;

                if (n[cur_node].children[i])
                {
                    n[n[cur_node].children[i]].suffix = n[s].children[i];
                    n[n[cur_node].children[i]].suffix_dist = s_dist;
                }
            }
        }
    }

    void match(char* pattern)
    {
        int begin = 0, end = 0;

        int cur_node = root;
        char* cur_char = pattern;
        while (*cur_char)
        {
            while (cur_node && !n[cur_node].child(*cur_char))
            {
                begin += n[cur_node].suffix_dist;
                cur_node = n[cur_node].suffix;
            }

            if (n[cur_node].child(*cur_char))
            {
                cur_node = n[cur_node].child(*cur_char);
                end++;

                int s = cur_node;
                int s_dist = 0;
                while (s)
                {
                    if (n[s].in_dict)
                    {
                        for (int i = begin + s_dist; i < end; ++i)
                            std::cout << pattern[i];
                        std::cout << std::endl;
                    }
                    s_dist += n[s].suffix_dist;
                    s = n[s].suffix;
                }
            }
            else
            {
                /* suffix가 root까지 올라갔는데도 못찾을 경우 */
                begin++;
                end++;
            }
            cur_char++;
        }
    }

    void print_sub(int cur_node, std::string& str)
    {
        if (n[cur_node].in_dict)
            std::cout << str.c_str() << std::endl;

        for (int i = 0; i < 26; ++i)
        {
            if (n[cur_node].children[i])
            {
                str += i + 'a';
                print_sub(n[cur_node].children[i], str);
                str.resize(str.size() - 1);
            }
        }
    }

    void print()
    {
        std::string str = "";
        print_sub(root, str);
    }
};

void aho_corasick()
{
    std::vector<char*>dict = { "a", "ab", "bab", "bc", "bca", "c", "caa" };

    trie t;
    for (auto x : dict)
        t.add(x);

    t.print();
    t.link();
    t.match("abcdcab");
}