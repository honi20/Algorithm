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

    ll n,m;
    ll arr[101];
    ll min=300003,result=0;
    
    cin>>n>>m;
    for (int i=0;i<n;i++)
        cin>>arr[i];
    
    for (int i=0;i<n-2;i++){
        for (int j=i+1;j<n-1;j++){
            for (int k=j+1;k<n;k++){
                if ((arr[i]+arr[j]+arr[k]) <= m && m - (arr[i]+arr[j]+arr[k]) < min){
                    result = arr[i]+arr[j]+arr[k];
                    min = m - (arr[i]+arr[j]+arr[k]);
                }
            }
        }
    }
    cout<<result;
}
