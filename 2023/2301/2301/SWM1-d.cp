#include <iostream>
#include <vector>
#include <cmath>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll t, dist;
    ll x1, y1, r1, x2, y2, r2;
    
    cin>>t;
    while (t--){
        cin>>x1>>y1>>r1>>x2>>y2>>r2;
        dist = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
        
        // 원 완전 겹침 => -1
        if (x1 == x2 && y1 == y2 && r1 == r2)
            cout<<"-1\n";
        
        // 외접 or 내접 => 1
        else if (dist == (r1 + r2) * (r1 + r2) || dist == abs(r1-r2) * abs(r1-r2))
            cout<<"1\n";
        
        // 아예 만나지 않음 => 0
        else if (dist > (r1 + r2) * (r1 + r2) || dist < abs(r1-r2) * abs(r1-r2) || (x1 == x2 && y1 == y2))
            cout<<"0\n";
        
        // 이외 => 2
        else
            cout<<"2\n";
    }
}
