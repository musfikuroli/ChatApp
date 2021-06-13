package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Objects;

public class TabsAccessorAdapter extends FragmentStateAdapter {


    public TabsAccessorAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1 : return new GroupsFragment();
            case 2 : return new ContactsFragment();
        }

        return new ChatsFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
