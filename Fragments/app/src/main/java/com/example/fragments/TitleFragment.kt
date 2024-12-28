package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.fragments.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
        binding.playButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment))

        /** overflow menu **/
        val menuHost: MenuHost = requireActivity()
        val menuProvider = object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                NavigationUI.onNavDestinationSelected(menuItem, findNavController())
                return true
            }
        }

        menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||  super.onOptionsItemSelected(item)
    }
}