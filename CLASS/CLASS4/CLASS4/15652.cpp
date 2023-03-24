#include <iostream>
#include <vector>
using namespace std;

int n, m;

void bt(vector <int> v, int cnt){
    if (cnt == m){
        for (int i=0;i<v.size();i++){
            cout<<v[i]<<" ";
        }
        cout<<"\n";
        return;
    }
    
    int num = v.back();
    vector <int> v2 = v;
    for (int i=num;i<=n;i++){
        v2.push_back(i);
        bt(v2, cnt+1);
        v2.pop_back();
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    
    vector<int> v;
    for (int i=1;i<=n;i++){
        v.push_back(i);
        bt(v, 1);
        v.pop_back();
    }
    
}
