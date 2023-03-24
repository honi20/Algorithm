#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <map>
using namespace std;
typedef long long ll;
#define MAX 1000000001

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n,x;
    map <int,int> m;
    
    cin>>n;
    while (n--){
        cin>>x;
        if (m.find(x) == m.end())
            m[x]=1;
        else
            m[x]++;
    }
    
    for (auto iter=m.begin();iter!=m.end();iter++){
        while (m[iter->first] > 0){
            cout<<iter->first<<"\n";
            m[iter->first]--;
        }
    }
}
