#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    cin>>n;
    
    if (n % 5 == 0)
        cout<<"-1";
    else
        cout<<5*(n - (n/5));
}
