#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
using namespace std;
typedef long long ll;


int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    string str;
    int cnt=0;
    
    getline(cin,str);
    for (int i=1;i<str.size();i++){
        if (str[i-1]==' ')
            cnt++;
    }
    if (str[0]==' ')
        cout<<cnt;
    else
        cout<<cnt+1;
}
