#include <vector>

struct disjoint_set
{
    struct node
    {
        node* parent;
        int rank;
        int size;
    };

    std::vector<node> nodes;

    disjoint_set(int n) : nodes(n) {}

    node* find(node* a)
    {
        if (a->parent == a) return a;
        return a->parent = find(a->parent);
    }

    void yunion(node* a, node* b)
    {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (a->rank < b->rank)
        {
            a->parent = b;
            b->size += a->size;
        }
        else if (a->rank > b->rank)
        {
            b->parent = a;
            a->size += b->size;
        }
        else
        {
            a->parent = b;
            b->rank++;
            b->size += a->size;
        }
    }
};