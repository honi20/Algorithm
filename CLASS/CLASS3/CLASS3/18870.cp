#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll n,x;
    vector <ll> arr1, arr2;
    map<ll,ll> m;
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>x;
        arr1.push_back(x);
        arr2.push_back(x);
    }
    sort(arr2.begin(), arr2.end());
    
    ll temp = 0;
    for (int i=0;i<arr2.size();i++){
        if (m.find(arr2[i]) == m.end()){
            m[arr2[i]]=temp;
            temp++;
        }
    }
    
    for (int i=0;i<arr1.size();i++){
        cout<<m[arr1[i]]<<" ";
    }
}
