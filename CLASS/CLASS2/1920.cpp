#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <map>
#include <set>
using namespace std;
typedef long long ll;
#define MAX 1000000001

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n,m,x;
    set <int> s;
    
    cin>>n;
    while (n--){
        cin>>x;
        s.insert(x);
    }
    cin>>m;
    while (m--){
        cin>>x;
        if (s.find(x)==s.end())
            cout<<"0\n";
        else
            cout<<"1\n";
    }
}
