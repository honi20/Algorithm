#include <iostream>
#include <vector>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, k, x;
    vector <int> val;
    int result = 0;
    
    cin>>n>>k;
    for (int i=0;i<n;i++){
        cin>>x;
        val.push_back(x);
    }
    
    int size = val.size();
    for (int i=size-1;i>=0;i--){
        if (k==0)
            break;
        
        if (k >= val[i]){
            result += (k/val[i]);
            k%=val[i];
        }
    }
    cout<<result;
}
