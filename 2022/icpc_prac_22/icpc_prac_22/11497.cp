#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
 
    int t, n;
    int arr[10002];
    
    cin>>t;
    while (t--){
        cin>>n;
        for (int i=0;i<n;i++)
            cin>>arr[i];
        
        sort(arr, arr+n, greater<int>());
        int right = arr[0];
        int left = arr[0];
        int result = 0;
        
        for (int i=1;i<n;i++){
            if (i%2 != 0){
                result = max(result, abs(right-arr[i]));
                right = arr[i];
            }
            else{
                result = max(result, abs(left-arr[i]));
                left = arr[i];
            }
        }
        result = max(result, abs(arr[n-1]-arr[n-2]));
        cout<<result<<"\n";
    }
    
}
