package com.tnb.f3r10.tutorinhome.tutorlist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.tnb.f3r10.tutorinhome.R;
import com.tnb.f3r10.tutorinhome.TutorInHomeApplication;
import com.tnb.f3r10.tutorinhome.entities.Tutor;
import com.tnb.f3r10.tutorinhome.login.ui.LoginActivity;
import com.tnb.f3r10.tutorinhome.tutordetail.TutorDetailActivity;
import com.tnb.f3r10.tutorinhome.tutorlist.TutorListPresenter;
import com.tnb.f3r10.tutorinhome.tutorlist.di.TutorListComponent;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.adapter.OnItemClickListener;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.adapter.TutorListAdapter;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TutorListActivity extends AppCompatActivity implements TutorListView, OnItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerViewTutors)
    RecyclerView recyclerViewTutors;
    @Bind(R.id.container)
    CoordinatorLayout container;

    TutorInHomeApplication app;
    TutorListPresenter presenter;
    TutorListAdapter adapter;
    TutorListComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list);
        ButterKnife.bind(this);
        app = (TutorInHomeApplication)getApplication();
        setUpInjection();
        setupToolbar();
        setupRecyclerView();
        presenter.onCreate();
        presenter.getTutors();
    }

    private void setupRecyclerView() {
        recyclerViewTutors.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTutors.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setUpInjection() {
        component = app.getTutorListComponent(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        presenter.signOff();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public void showList() {
        recyclerViewTutors.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerViewTutors.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String msg) {
        showSnackBar(msg);
    }

    private void showSnackBar(String msg) {
        Snackbar.make(container, msg, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void setTutors(List<Tutor> data) {
        adapter.setTutors(data);
    }

    @Override
    public void onItemClick(Tutor tutor) {
        navigateToDetailScreen(tutor);
    }

    public void navigateToDetailScreen(Tutor tutor){
        Intent mIntent = new Intent(this, TutorDetailActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(app.getTutorKey(), Parcels.wrap(tutor));
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
    }

    @Override
    public void onItemLongClick(Tutor tutor) {

    }

    public TutorListPresenter getPresenter() {
        return component.getTutorListPresenter();
    }

    public TutorListAdapter getAdapter() {
        return component.getTutorListAdapter();
    }
}
