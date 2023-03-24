#include <iostream>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
#define R 31
#define MAX 1234567891

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    string str;
    cin>>n>>str;
    
    ll result = 0;
    for (int i=0; i<str.size(); i++){
        ll alp = (int)(str[i]-'a') + 1;
//        cout<<alp<<" ";
        ll tmp = 1;
        for (int j=0; j<i; j++){
            tmp *= R;
            tmp %= MAX;
        }
        
        alp = alp * tmp % MAX;
//        cout<<alp<<" ";
        result += alp;
        result %= MAX;
    }
    cout<<result;
    
    return 0;
}
