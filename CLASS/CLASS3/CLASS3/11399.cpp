#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n,x;
    vector <int> v;
    int result=0;
    int sum[1002];
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>x;
        v.push_back(x);
    }
    sort(v.begin(), v.end());
    
    sum[0]=v[0];
    result = v[0];
    for (int i=1;i<v.size();i++){
        sum[i]=sum[i-1]+v[i];
        result += sum[i];
    }
    cout<<result;
}
