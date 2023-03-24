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
    double arr[1005];
    double max=0;
    double sum=0;
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>arr[i];
        if (max<arr[i])
            max=arr[i];
    }
    
    for (int i=0;i<n;i++){
        sum+=(arr[i]/max*100);
    }
    cout<<fixed;
    cout.precision(2);
    cout<<sum/n;
}
