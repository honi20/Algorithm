#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n,m;
int arr[11];
bool visited[11];

void bt(vector<int> v, int cnt){
    if (cnt == m){
        for (int i=0; i<v.size(); i++){
            cout<<v[i]<<" ";
        }
        cout<<"\n";
        return;
    }
    
    for (int i=0;i<n;i++){
        if (!visited[i]){
            v.push_back(arr[i]);
            visited[i]=true;
            bt(v, cnt+1);
            v.pop_back();
            visited[i]=false;
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=0;i<n;i++)
        cin>>arr[i];
    
    sort(arr, arr+n);
    vector<int> v;
    for (int i=0;i<n;i++){
        v.push_back(arr[i]);
        visited[i]=true;
        bt(v, 1);
        v.pop_back();
        visited[i]=false;
    }
    
}
