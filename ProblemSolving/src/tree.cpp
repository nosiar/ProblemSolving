#include <vector>
#include <queue>

template<typename T>
struct treap
{
    struct node
    {
        T val;
        int priority;
        int size = 1;
        node* left = nullptr;
        node* right = nullptr;

        node(T v) : val(v), priority(rand()) {}
    };

    node* root = nullptr;

    void insert(T val)
    {
        if (!root)
        {
            root = new node(val);
            return;
        }

        node* new_node = new node(val);
        node* prev = nullptr;
        node* cur = root;
        bool left;
        while (cur && cur->priority > new_node->priority)
        {
            cur->size++;

            left = cur->val > new_node->val;
            prev = cur;
            if (left)
                cur = cur->left;
            else
                cur = cur->right;
        }        

        if (cur)
        {
            auto children = insert_sub(val, cur);
            new_node->left = children.first;
            new_node->right = children.second;
            new_node->size = 1;
            if (new_node->left)
                new_node->size += new_node->left->size;
            if (new_node->right)
                new_node->size += new_node->right->size;
        }

        if (prev)
        {
            if (left) prev->left = new_node;
            else prev->right = new_node;
        }
        else
            root = new_node;
    }

    std::pair<node*, node*> insert_sub(T new_val, node* old_node)
    {
        if (!old_node) return{ nullptr, nullptr };

        if (old_node->val < new_val)
        {
            auto children = insert_sub(new_val, old_node->right);
            old_node->right = children.first;
            old_node->size = 1;
            if (old_node->left)
                old_node->size += old_node->left->size;
            if (old_node->right)
                old_node->size += old_node->right->size;
            return { old_node, children.second };
        }
        else
        {
            auto children = insert_sub(new_val, old_node->left);
            old_node->left = children.second;
            old_node->size = 1;
            if (old_node->left)
                old_node->size += old_node->left->size;
            if (old_node->right)
                old_node->size += old_node->right->size;
            return{ children.first, old_node };
        }
    }

    /* 지울 val이 무조건 들어있다고 가정 */
    void erase(T val)
    {
        node* prev = nullptr;
        node* cur = root;
        bool left;
        while (cur && cur->val != val)
        {
            cur->size--;

            left = cur->val > val;
            prev = cur;
            if (left)
                cur = cur->left;
            else
                cur = cur->right;
        }

        node* new_node = erase_sub(cur->left, cur->right);
        delete cur;

        if (prev)
        {
            if (left) prev->left = new_node;
            else prev->right = new_node;
        }
        else
            root = new_node;
    }

    node* erase_sub(node* left_node, node* right_node)
    {
        if (!left_node) return right_node;
        if (!right_node) return left_node;

        if (left_node->priority < right_node->priority)
        {
            right_node->size += left_node->size;
            right_node->left = erase_sub(left_node, right_node->left);
            return right_node;
        }
        else
        {
            left_node->size += right_node->size;
            left_node->right = erase_sub(left_node->right, right_node);
            return left_node;
        }
    }

    ~treap()
    {
        std::queue<node*> q;
        q.push(root);
        while (!q.empty())
        {
            node* x = q.front(); q.pop();
            if (x->left) q.push(x->left);
            if (x->right) q.push(x->right);
            delete x;
        }
    }
};