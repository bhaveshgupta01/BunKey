package com.example.bunkey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bunkey.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Perform.setOnClickListener{hq()}
    }

    private fun hq()
    {
        val inpK:List<Char> =listOf('*','\n','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','@','!',' ','0','1','2','3','4','5','6','7','8','9','.','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',',','?')
        val encK:List<Char> =listOf('A','C','E','F','G','H','I','J','K','L','M','O','P','Q','R','S','T','U','V','W','X','Y','Z','!','#','0','1','2','3','4','5','6','7','8','9','@')
        val arr: MutableList<Char> = binding.InputText.text.toString().toMutableList()
        var fin=""
        if(binding.action.checkedRadioButtonId==R.id.encrypt)
        {
            while(arr.isNotEmpty())
            {
                var lst = mutableListOf<Char>()
                lst = if(arr.size<6) {
                    arr
                } else {
                    arr.subList(0,5)
                }

                val hel=multi(lst,inpK,70)
                val arr=listOf('B','D','N')
                fin+=david(hel,encK,36)+arr.random()
                lst.removeAll(lst)
            }
        }
        else if(binding.action.checkedRadioButtonId==R.id.decrypt)//Decrypt
        {
            var i=0
            val n=arr.size
            var bre=0
            while(i<n)
            {
                val lst = mutableListOf<Char>()
                if(arr[i]=='B'||arr[i]=='D'||arr[i]=='N')
                {
                    lst.addAll(arr.subList(bre,i))
                    val hel=multi(lst,encK,36)
                    fin+=david(hel,inpK,70)
                    bre=i+1
                    lst.removeAll(lst)
                }
                i++
            }
        }
        binding.ResultText.text=fin
    }



    private fun multi(Str:MutableList<Char>, arr:List<Char>, m:Int): Int {
        val mun=Str.size
        var i=0
        var an=0.0
        val n=m.toDouble()
        while(i<mun)
        {
            an+=arr.indexOf(Str.last())*(n.pow(i))
            Str.removeAt(Str.size-1)
            i++
        }
        return an.toInt()
    }

    private fun david(St:Int, arr:List<Char>, n:Int): String {
        var an=""
        var str=St
        while (str>0)
        {
            val k=str%n
            val hig=arr[k]
            an=hig+an
            str /= n
        }
        return an
    }
}

