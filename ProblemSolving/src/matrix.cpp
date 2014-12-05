#include <vector>
#include <iostream>

template<class T>
struct square_mat final
{
    square_mat(int s) : size(s)
    {
        mat.resize(s);
        for (auto& row : mat)
            row.resize(s);
    }

    std::vector<T>& operator[](int r)
    {
        return mat[r];
    }

    const std::vector<T>& operator[](int r) const
    {
        return const_cast<square_mat&>(*this)[r];
    }

    square_mat operator*(const square_mat& other) const
    {
        square_mat ret(size);
        for (int i = 0; i < size; ++i)
        {
            for (int j = 0; j < size; ++j)
            {
                ret[i][j] = T{};
                for (int k = 0; k < size; ++k)
                    ret[i][j] += mat[i][k] * other.mat[k][j];
            }
        }
        return ret;
    }

    square_mat& operator*=(const square_mat& other)
    {
        square_mat temp = *this * other;
        this->mat = std::move(temp.mat);
        return *this;
    }

    square_mat pow(int k)
    {
        square_mat temp = *this;
        int bit = 30;
        while ((k >> bit & 1) == 0) bit--;
        bit--;
        while (bit >= 0)
        {
            temp *= temp;
            if (k >> bit & 1)
                temp *= *this;
            bit--;
        }
        return temp;
    }

    std::vector<std::vector<T>> mat;
    int size;
};

template<class T>
std::ostream& operator<<(std::ostream& os, const square_mat<T>& sm)
{
    for (int i = 0; i < sm.size; ++i)
    {
        for (int j = 0; j < sm.size; ++j)
            std::cout << sm.mat[i][j] << ' ';
        std::cout << std::endl;
    }
    std::cout << std::endl;
    return os;
}