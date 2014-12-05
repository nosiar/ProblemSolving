#include <cstring>
#include <vector>

std::vector<int> partial_match(char* word)
{
    int len = strlen(word);

    std::vector<int> table(len);

    int matched = 0;

    for (int pos = 1; pos < len; ++pos)
    {
        while (matched > 0 && word[pos] != word[matched])
            matched = table[matched - 1];

        if (word[pos] == word[matched])
        {
            matched++;
            table[pos] = matched;
        }
    }

    return table;
}

std::vector<int> kmp(char* str, char* word)
{
    std::vector<int> ret;

    int len = strlen(str);
    int word_len = strlen(word);

    auto partial_table = partial_match(word);

    int matched = 0;

    for (int pos = 0; pos < len; ++pos)
    {
        while (matched > 0 && str[pos] != word[matched])
            matched = partial_table[matched - 1];

        if (str[pos] == word[matched])
        {
            matched++;

            /* 다음 루프에서 word의 범위를 벗어난 곳을 접근하지만
             * C 문자열은 거기에 0이 들어있어서 어차피 상관 없다..?  */
            if (matched == word_len)
                ret.push_back(pos - matched + 1);
        }
    }

    return ret;
}