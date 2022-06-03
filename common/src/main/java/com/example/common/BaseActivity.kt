package com.example.common

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<Binding : ViewDataBinding, VM : BaseViewModule> : AppCompatActivity() {
    lateinit var binding: Binding
    abstract val layoutResourceId : Int

    @Inject
    lateinit var vmFactory : ViewModelProvider.Factory
    abstract val vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this), layoutResourceId,null,false
        )
        binding.lifecycleOwner = this
        AndroidInjection.inject(this)
        binding.setVariable(BR.vm,vm)
        setContentView(binding.root)

        //Observe Navigation Event
//        vm.navigationEvent.observe(this){
//            vm.handleNavigationParams(it)
//        }
//
//        vm.navigationEvent.postValue(intent)

    }


}