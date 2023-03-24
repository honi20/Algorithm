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

    int n;
    int arr[1000005];
    
    cin>>n;
    for (int i=0;i<n;i++)
        cin>>arr[i];
    
    sort(arr,arr+n);
    cout<<arr[0]<<" "<<arr[n-1];
    
}
