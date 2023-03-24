#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll W, H;
    ll f, c;
    ll x1, y1, x2, y2;
    
    cin>>W>>H>>f>>c>>x1>>y1>>x2>>y2;
    
    ll result = 0;
    if (x1 < W-f){
        if (x2 <= W-f){
            result += ((x2-x1) * (y2-y1));
        }
        else{
            result += ((W-f-x1) * (y2-y1));
        }
    }
    
    if (x1 < f){
        if (x2 <= f){
            result += ((x2-x1) * (y2-y1));
        }
        else{
            result += ((f-x1) * (y2-y1));
        }
    }
    
    cout<<W*H - result*(c+1);
}
 
