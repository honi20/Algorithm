#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

string str;


vector <int> Fail(string search){
    vector<int> Pi(search.length(), 0);
    
    for (int i=1, j=0; i<search.length(); i++){
        while (j>0 && search[i]!=search[j])
            j=Pi[j-1];
        if (search[i] == search[j])
            Pi[i]=++j;
    }
    return Pi;
}

vector <int> KMP(string text, string search){
    vector<int> Pi = Fail(search);
    vector<int> Ans;
    
    for (int i=0,j=0; i<text.length(); i++){
        while (j>0 && text[i]!=search[j])
            j=Pi[j-1];
        
        if (text[i] == search[j]){
            if (j == search.length()-1){
                Ans.push_back(i-j);
                j=Pi[j];
            }
            else
                j++;
        }
    }
    return Ans;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    while (true){
        cin>>str;
        if (str == ".")
            break;
        
        vector<int> Pi = Fail(str);
        int len = str.size();
        
        if (len % (len - Pi[len-1]) != 0)
            cout<<"1\n";
        else
            cout<<len/(len-Pi[len-1])<<"\n";
    }
    
    return 0;
}

