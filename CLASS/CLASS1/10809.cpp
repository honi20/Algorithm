#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int num[26];
    string str;
    cin>>str;
    
    for (int i=0;i<26;i++)
        num[i]=-1;
    
    for (int i=0;i<str.size();i++){
        if (num[str[i]-'a']==-1)
            num[str[i]-'a']=i;
    }
    for (int i=0;i<26;i++)
        cout<<num[i]<<" ";
}
