2295
#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#include <algorithm>
#include <set>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, x;
    vector <ll> v;
    
    cin>>n;
    for (int i=0; i<n; i++){
        cin>>x;
        v.push_back(x);
    }
    
    vector <ll> sum;
    for (int i=0; i<n; i++){
        for (int j=i+1; j<n; j++){
            for (int k=j+1; k<n; k++){
                sum.push_back(v[i]+v[j]+v[k]);
            }
        }
    }
    
    sort(sum.begin(), sum.end(), greater<>());
    for (int i=0; i<sum.size(); i++){
        for (int j=0; j<n; j++){
            if (v[j] == sum[i]){
                cout<<sum[i];
                return 0;
            }
        }
    }
    
    return 0;
}
