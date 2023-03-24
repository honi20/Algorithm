#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;
#define MAX 1000000001

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int t,h,w,n;
    cin>>t;
    
    while(t--){
        cin>>h>>w>>n;
        
        if (n%h == 0){
            if (n/h<10){
                cout<<h<<"0"<<n/h<<"\n";
            }
            else
                cout<<h<<n/h<<"\n";
        }
        
        else{
            if (n/h<9){
                cout<<n%h<<"0"<<n/h+1<<"\n";
            }
            else
                cout<<n%h<<n/h+1<<"\n";
        }
    }
}
