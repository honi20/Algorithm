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

    string n;
    while (1){
        cin>>n;
        if (n=="0")
            break;
        
        int size=n.size();
        bool flag=true;
        for (int i=0;i<size/2;i++){
            if (n[i]!=n[size-i-1]){
                flag=false;
                break;
            }
        }
        if (flag)
            cout<<"yes\n";
        else
            cout<<"no\n";
    }
}
