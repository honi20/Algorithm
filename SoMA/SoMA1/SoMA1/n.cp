#include <iostream>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;

int n;
vector <pair<int,int>> v[10002];
int resultW = 0;
int resultP = 0;



int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int x, y, c;
    
    cin>>n;
    for (int i=0; i<n-1; i++){
        cin>>x>>y>>c;
        v[x].push_back({y, c});
        v[y].push_back({x, c});
    }

    
    
    return 0;
}
