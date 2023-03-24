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

    int t,n;
    string str;
    
    cin>>t;
    while (t--){
        cin>>n>>str;
        
        for (int i=0;i<str.size();i++){
            for (int k=0;k<n;k++)
                cout<<str[i];
        }
        cout<<"\n";
    }
}
