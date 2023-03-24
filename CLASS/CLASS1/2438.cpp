#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
using namespace std;
typedef long long ll;


int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n;
    cin>>n;
    for (int i=1;i<=n;i++){
        for (int j=1;j<=i;j++)
            cout<<"*";
        cout<<"\n";
    }
}
