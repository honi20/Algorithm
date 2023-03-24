#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
 
    ll n, t, result=0;
    ll arr[1000002] = {0,};
    vector <pair<ll,ll>> v;
    bool num[1000002];

    cin>>t;
    while (t--){
        cin>>n;
        
        v.clear();
        result = 0;
        
        for (int i=0;i<n;i++){
            num[i] = false;
            cin>>arr[i];
            v.push_back({(-1 * arr[i]), i});
        }
        
        sort(v.begin(), v.end());
        for (int i=0; i<v.size(); i++){
            ll idx = v[i].second;
            if (num[idx])
                continue;
            
            ll sum = 0;
            ll cnt = 0;
            ll temp = idx;
            while (true){
                temp--;
                if (temp < 0)
                    break;
                
                if (num[temp])
                    break;
                
                num[temp] = true;
                sum += arr[temp];
                cnt++;
            }
            result += (arr[idx]*cnt - sum);
            num[idx]=true;
        }
        cout<<result<<"\n";
    }
}
