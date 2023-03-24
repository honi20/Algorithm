#include <iostream>
#include <set>
using namespace std;
typedef long long ll;

set <ll> s;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
 
    ll n;
    
    cin>>n;
    bool flag = true;
    
    while (true){
        ll sum = 0;
        while (n>0){
            sum += ((n%10)*(n%10));
            n /= 10;
        }
        
        if (s.find(sum) != s.end()){
            flag = false;
            break;
        }
        
        if (sum == 1)
            break;
        
        s.insert(sum);
        n = sum;
    }
    
    if (flag)
        cout<<"HAPPY";
    else
        cout<<"UNHAPPY";
}
