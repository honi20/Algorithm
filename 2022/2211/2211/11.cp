#include <iostream>
using namespace std;
#define MAX 987654321

int t, n;
int arr1[300003];
int arr2[300003];
int dp1[300003];
int dp2[300003];

int s, e, maxNum, maxIdx;
int group1Cnt = 0, group2Cnt = 0;
int group1[300003][4];
int group2[300003][4];


void sumArr(){
    // arr1
    dp1[0] = arr1[0];
    s = 0;
    e = 0;
    maxNum = arr1[0];
    maxIdx = 0;
    
    for (int i=1; i<n; i++){
        if (arr1[i] > arr1[i] + dp1[i-1]){
            dp1[i] = arr1[i];
            group1[group1Cnt][0] = s;
            group1[group1Cnt][1] = e;
            group1[group1Cnt][2] = maxNum;
            group1[group1Cnt][3] = maxIdx;
            
            s = e = i;
            maxNum = dp1[i];
            maxIdx = i;
            group1Cnt++;
        }
            
        else{
            dp1[i] = arr1[i] + dp1[i-1];
            e = i;
            if (dp1[i] > maxNum){
                maxNum = dp1[i];
                maxIdx = i;
            }
            
            if (i == n-1){
                group1[group1Cnt][0] = s;
                group1[group1Cnt][1] = e;
                group1[group1Cnt][2] = maxNum;
                group1[group1Cnt][3] = maxIdx;
            }
        }
    }
    
    // arr2
    dp2[0] = arr2[0];
    s = 0;
    e = 0;
    maxNum = arr2[0];
    maxIdx = 0;
    
    for (int i=1; i<n; i++){
        if (arr2[i] > arr2[i] + dp2[i-1]){
            dp2[i] = arr2[i];
            group2[group2Cnt][0] = s;
            group2[group2Cnt][1] = e;
            group2[group2Cnt][2] = maxNum;
            group2[group2Cnt][3] = maxIdx;
            
            s = e = i;
            maxNum = arr2[i];
            maxIdx = i;
            group2Cnt++;
        }
            
        else{
            dp2[i] = arr2[i] + dp2[i-1];
            e = i;
            if (dp2[i] > maxNum){
                maxNum = dp2[i];
                maxIdx = i;
            }
            
            if (i == n-1){
                group2[group2Cnt][0] = s;
                group2[group2Cnt][1] = e;
                group2[group2Cnt][2] = maxNum;
                group2[group2Cnt][3] = maxIdx;
            }
        }
    }
}

// arr1 -> arr2
int solve1(){
    int nowmax, nows, nowe, summax;
    int result = -MAX;
    
    for (int i=0; i<=group1Cnt; i++){
        nowmax = group1[i][2];
        nows = group1[i][0];
        nowe = group1[i][3];
        summax = -MAX;
        
        for (int j=0; j<=group2Cnt; j++){
            
            if (group2[j][1] < nows){
                summax = max(summax, nowmax+group2[j][2]);
            }
            else if (group2[j][0] > nowe){
                summax = max(summax, nowmax+group2[j][2]);
            }
            else if (group2[j][0] < nows && group2[j][1] < nowe){
                int tmp = -MAX;
                for (int k=group2[j][0]; k<=group2[j][1]; k++){
                    tmp = max(tmp, dp2[k]);
                }
                summax = max(summax, nowmax+tmp);
            }
            else if (group2[j][0] > nows && group2[j][1] > nowe){
                int tmpmax = -MAX;
                int tmpmin = dp2[nows];
                for (int k=group2[j][0]; k<group2[j][1]; k++){
                    tmpmax = max(tmpmax, dp2[k]);
                    tmpmin = min(tmpmin, dp2[k]);
                }
                tmpmax = max(tmpmax, dp2[group2[j][1]]);
                summax = max(summax, nowmax+(dp2[tmpmax] - dp2[tmpmin]));
            }
            else{
                int tmp = -MAX;
                for (int k=group2[j][0]; k<=group2[j][1]; k++){
                    tmp = max(tmp, dp2[k]);
                }
                summax = max(summax, nowmax+tmp);
                int tmpmax = -MAX;
                int tmpmin = dp2[nows];
                for (int k=group2[j][0]; k<group2[j][1]; k++){
                    tmpmax = max(tmpmax, dp2[k]);
                    tmpmin = min(tmpmin, dp2[k]);
                }
                tmpmax = max(tmpmax, dp2[group2[j][1]]);
                summax = max(summax, nowmax+(dp2[tmpmax] - dp2[tmpmin]));
            }
        }
        result = max(result, summax);
    }
    return result;
}

// arr2 -> arr1
int solve2(){
    int nowmax, nows, nowe, summax;
    int result = -MAX;
    
    for (int i=0; i<=group2Cnt; i++){
        nowmax = group2[i][2];
        nows = group2[i][0];
        nowe = group2[i][3];
        summax = -MAX;
        
        for (int j=0; j<=group1Cnt; j++){
            
            if (group1[j][1] < nows){
                summax = max(summax, nowmax+group1[j][2]);
            }
            else if (group1[j][0] > nowe){
                summax = max(summax, nowmax+group1[j][2]);
            }
            else if (group1[j][0] < nows && group1[j][1] < nowe){
                int tmp = -MAX;
                for (int k=group1[j][0]; k<s; k++){
                    tmp = max(tmp, dp1[k]);
                }
                summax = max(summax, nowmax+tmp);
            }
            else if (group1[j][0] > nows && group1[j][1] > nowe){
                int tmpmax = -MAX;
                int tmpmin = dp1[nows];
                for (int k=nows+1; k<group1[j][1]; k++){
                    tmpmax = max(tmpmax, dp1[k]);
                    tmpmin = min(tmpmin, dp1[k]);
                }
                tmpmax = max(tmpmax, dp1[group1[j][1]]);
                summax = max(summax, nowmax+(dp1[tmpmax] - dp1[tmpmin]));
            }
            else{
                int tmp = -MAX;
                for (int k=group1[j][0]; k<s; k++){
                    tmp = max(tmp, dp1[k]);
                }
                summax = max(summax, nowmax+tmp);
                int tmpmax = -MAX;
                int tmpmin = dp1[nows];
                for (int k=nows+1; k<group1[j][1]; k++){
                    tmpmax = max(tmpmax, dp1[k]);
                    tmpmin = min(tmpmin, dp1[k]);
                }
                tmpmax = max(tmpmax, dp1[group1[j][1]]);
                summax = max(summax, nowmax+(dp1[tmpmax] - dp1[tmpmin]));
            }
        }
        result = max(result, summax);
    }
    return result;
}


int main() {
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    cin>>t;
    for (int x=1; x<=t; x++){
        cin>>n;
        
        for (int i=0; i<n; i++)
            cin>>arr1[i];
        
        for (int i=0; i<n; i++)
            cin>>arr2[i];

        sumArr();
        cout<<max(solve1(),solve2())<<"\n\n";
        
    }
}



