#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int a,b,c;
    while(1){
        cin>>a>>b>>c;
        if (a==0&&b==0&&c==0)
            break;
        
        int temp=max(a,max(b,c));
        if (temp==a){
            if (a*a==b*b+c*c)
                cout<<"right\n";
            else
                cout<<"wrong\n";
        }
        else if (temp==b){
            if (b*b==a*a+c*c)
                cout<<"right\n";
            else
                cout<<"wrong\n";
        }
        else if (temp==c){
            if (c*c==b*b+a*a)
                cout<<"right\n";
            else
                cout<<"wrong\n";
        }
    }
}
