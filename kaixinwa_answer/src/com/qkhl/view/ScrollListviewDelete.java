package com.qkhl.view;

import com.qkhl.kaixinwa_android.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.AbsListView.OnScrollListener;

public class ScrollListviewDelete extends ListView {
	// implements OnScrollListener
	private float minDis = 10;
	private float mLastMotionX;// 记住上次X触摸屏的位置
	private float mLastMotionY;// 记住上次Y触摸屏的位置
	private float mLastY = -1;
	private boolean isLock = false;

	// private XListViewHeader mHeaderView;
	// private XListViewFooter mFooterView;
	// private Scroller mScroller;
	// private int mTotalItemCount;
	// private RelativeLayout mHeaderViewContent;
	// private int mHeaderViewHeight;
	// private boolean mIsFooterReady = false;
	// private boolean mEnablePullRefresh = true;
	// private boolean mPullRefreshing = false;
	// private boolean mEnablePullLoad;
	// private boolean mPullLoading;
	// private OnScrollListener mScrollListener;
	// private IXListViewListener mListViewListener;
	// private int mScrollBack;
	// private final static int SCROLLBACK_HEADER = 0;
	// private final static int SCROLLBACK_FOOTER = 1;
	//
	// private final static int SCROLL_DURATION = 50; // scroll back duration
	// private final static int PULL_LOAD_MORE_DELTA = 50; // when pull up >=
	// 50px
	// // at bottom, trigger
	// // load more.
	// private final static float OFFSET_RADIO = 1.8f; // support iOS like pull
	// // feature.

	public ScrollListviewDelete(Context context, AttributeSet attrs) {
		super(context, attrs);
		// initWithContext(context);
	}

	// private void initWithContext(Context context) {
	// mScroller = new Scroller(context, new DecelerateInterpolator());
	// // XListView need the scroll event, and it will dispatch the event to
	// // user's listener (as a proxy).
	// super.setOnScrollListener(this);
	//
	// // init header view
	// mHeaderView = new XListViewHeader(context);
	// mHeaderViewContent = (RelativeLayout) mHeaderView
	// .findViewById(R.id.xlistview_header_content);
	// addHeaderView(mHeaderView);
	//
	// // init footer view
	// mFooterView = new XListViewFooter(context);
	//
	// // init header height
	// mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
	// new OnGlobalLayoutListener() {
	// public void onGlobalLayout() {
	// mHeaderViewHeight = mHeaderViewContent.getHeight();
	// getViewTreeObserver()
	// .removeGlobalOnLayoutListener(this);
	// }
	// });
	// }
	//
	//

	/**
	 * 如果一个ViewGroup的onInterceptTouchEvent()方法返回true，说明Touch事件被截获，
	 * 子View不再接收到Touch事件，而是转向本ViewGroup的
	 * onTouchEvent()方法处理。从Down开始，之后的Move，Up都会直接在onTouchEvent()方法中处理。
	 * 先前还在处理touch event的child view将会接收到一个 ACTION_CANCEL。
	 * 如果onInterceptTouchEvent()返回false，则事件会交给child view处理。
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (!isIntercept(ev)) {
			return false;
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		boolean dte = super.dispatchTouchEvent(event);
		if (MotionEvent.ACTION_UP == event.getAction() && !dte) {// onItemClick
			int position = pointToPosition((int) event.getX(),
					(int) event.getY());
			View view = getChildAt(position);
			super.performItemClick(view, position, view.getId());
		}
		return dte;
	}

	@Override
	// 处理点击事件，如果是手势的事件则不作点击事件 普通View
	public boolean performClick() {
		return super.performClick();
	}

	@Override
	// 处理点击事件，如果是手势的事件则不作点击事件 ListView
	public boolean performItemClick(View view, int position, long id) {
		return super.performItemClick(view, position, id);
	}

	/**
	 * 检测是ListView滑动还是item滑动 isLock 一旦判读是item滑动，则在up之前都是返回false
	 */
	private boolean isIntercept(MotionEvent ev) {
		float x = ev.getX();
		float y = ev.getY();
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.e("test", "isIntercept  ACTION_DOWN  " + isLock);
			mLastMotionX = x;
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e("test", "isIntercept  ACTION_MOVE  " + isLock);
			if (!isLock) {
				float deltaX = Math.abs(mLastMotionX - x);
				float deltay = Math.abs(mLastMotionY - y);
				mLastMotionX = x;
				mLastMotionY = y;
				if (deltaX > deltay && deltaX > minDis) {
					isLock = true;
					return false;
				}
			} else {
				return false;
			}
			break;
		case MotionEvent.ACTION_UP:
			Log.e("test", "isIntercept  ACTION_UP  " + isLock);
			isLock = false;
			break;
		case MotionEvent.ACTION_CANCEL:
			Log.e("test", "isIntercept  ACTION_CANCEL  " + isLock);
			isLock = false;
			break;
		}
		return true;
	}

	// @Override
	// public void setAdapter(ListAdapter adapter) {
	// // make sure XListViewFooter is the last footer view, and only add once.
	// if (mIsFooterReady == false) {
	// mIsFooterReady = true;
	// addFooterView(mFooterView);
	// }
	// super.setAdapter(adapter);
	// }
	//
	// /**
	// * enable or disable pull down refresh feature.
	// *
	// * @param enable
	// */
	// public void setPullRefreshEnable(boolean enable) {
	// mEnablePullRefresh = enable;
	// if (!mEnablePullRefresh) { // disable, hide the content
	// mHeaderViewContent.setVisibility(View.INVISIBLE);
	// } else {
	// mHeaderViewContent.setVisibility(View.VISIBLE);
	// }
	// }
	//
	// /**
	// * enable or disable pull up load more feature.
	// *
	// * @param enable
	// */
	// public void setPullLoadEnable(boolean enable) {
	// mEnablePullLoad = enable;
	// if (!mEnablePullLoad) {
	// mFooterView.hide();
	// mFooterView.setOnClickListener(null);
	// } else {
	// mPullLoading = false;
	// mFooterView.show();
	// mFooterView.setState(XListViewFooter.STATE_NORMAL);
	// // both "pull up" and "click" will invoke load more.
	// mFooterView.setOnClickListener(new OnClickListener() {
	// public void onClick(View v) {
	// startLoadMore();
	// }
	// });
	// }
	// }
	//
	// /**
	// * stop refresh, reset header view.
	// */
	// public void stopRefresh() {
	// if (mPullRefreshing == true) {
	// mPullRefreshing = false;
	// resetHeaderHeight();
	// }
	// }
	//
	// /**
	// * stop load more, reset footer view.
	// */
	// public void stopLoadMore() {
	// if (mPullLoading == true) {
	// mPullLoading = false;
	// mFooterView.setState(XListViewFooter.STATE_NORMAL);
	// mFooterView.hide();
	// }
	// }
	//
	// /**
	// * set last refresh time
	// *
	// * @param time
	// */
	// public void setRefreshTime(String time) {
	//
	// }
	//
	// private void invokeOnScrolling() {
	// if (mScrollListener instanceof OnXScrollListener) {
	// OnXScrollListener l = (OnXScrollListener) mScrollListener;
	// l.onXScrolling(this);
	// }
	// }
	//
	// private void updateHeaderHeight(float delta) {
	// mHeaderView.setVisiableHeight((int) delta
	// + mHeaderView.getVisiableHeight());
	// if (mEnablePullRefresh && !mPullRefreshing) { // 未处于刷新状态，更新箭头
	// if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
	// mHeaderView.setState(XListViewHeader.STATE_READY);
	// } else {
	// mHeaderView.setState(XListViewHeader.STATE_NORMAL);
	// }
	// }
	// setSelection(0); // scroll to top each time
	// }
	//
	// /**
	// * reset header view's height.
	// */
	// private void resetHeaderHeight() {
	// int height = mHeaderView.getVisiableHeight();
	// if (height == 0) // not visible.
	// return;
	// // refreshing and header isn't shown fully. do nothing.
	// if (mPullRefreshing && height <= mHeaderViewHeight) {
	// return;
	// }
	// int finalHeight = 0; // default: scroll back to dismiss header.
	// // is refreshing, just scroll back to show all the header.
	// if (mPullRefreshing && height > mHeaderViewHeight) {
	// finalHeight = mHeaderViewHeight;
	// }
	// mScrollBack = SCROLLBACK_HEADER;
	// mScroller.startScroll(0, height, 0, finalHeight - height,
	// SCROLL_DURATION);
	// // trigger computeScroll
	// invalidate();
	// }
	//
	// private void updateFooterHeight(float delta) {
	// int height = mFooterView.getBottomMargin() + (int) delta;
	// if (mEnablePullLoad && !mPullLoading) {
	// if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load
	// // more.
	// mFooterView.setState(XListViewFooter.STATE_READY);
	// } else {
	// mFooterView.setState(XListViewFooter.STATE_NORMAL);
	// }
	// }
	// mFooterView.setBottomMargin(height);
	//
	// // setSelection(mTotalItemCount - 1); // scroll to bottom
	// }
	//
	// private void resetFooterHeight() {
	// int bottomMargin = mFooterView.getBottomMargin();
	// if (bottomMargin > 0) {
	// mScrollBack = SCROLLBACK_FOOTER;
	// mScroller.startScroll(0, bottomMargin, 0, -bottomMargin,
	// SCROLL_DURATION);
	// invalidate();
	// }
	// }
	//
	// private void startLoadMore() {
	// mPullLoading = true;
	// mFooterView.setState(XListViewFooter.STATE_LOADING);
	// if (mListViewListener != null) {
	// mListViewListener.onLoadMore();
	// }
	// }
	//
	// @Override
	// public boolean onTouchEvent(MotionEvent ev) {
	// if (mLastY == -1) {
	// mLastY = ev.getRawY();
	// }
	//
	// switch (ev.getAction()) {
	// case MotionEvent.ACTION_DOWN:
	// mLastY = ev.getRawY();
	// break;
	// case MotionEvent.ACTION_MOVE:
	// final float deltaY = ev.getRawY() - mLastY;
	// mLastY = ev.getRawY();
	// if (getFirstVisiblePosition() == 0
	// && (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
	// // the first item is showing, header has shown or pull down.
	// updateHeaderHeight(deltaY / OFFSET_RADIO);
	// invokeOnScrolling();
	// } else if (getLastVisiblePosition() == mTotalItemCount - 1
	// && (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
	// // last item, already pulled up or want to pull up.
	// mFooterView.show();
	// updateFooterHeight(-deltaY / OFFSET_RADIO);
	// }
	// break;
	// default:
	// mLastY = -1; // reset
	// if (getFirstVisiblePosition() == 0) {
	// // invoke refresh
	// if (mEnablePullRefresh
	// && mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
	// mPullRefreshing = true;
	// mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
	// if (mListViewListener != null) {
	// mListViewListener.onRefresh();
	// }
	// }
	// resetHeaderHeight();
	// } else if (getLastVisiblePosition() == mTotalItemCount - 1) {
	// // invoke load more.
	// if (mEnablePullLoad
	// && mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA) {
	// startLoadMore();
	// }
	// resetFooterHeight();
	// }
	// break;
	// }
	// return super.onTouchEvent(ev);
	// }
	//
	// @Override
	// public void computeScroll() {
	// if (mScroller.computeScrollOffset()) {
	// if (mScrollBack == SCROLLBACK_HEADER) {
	// mHeaderView.setVisiableHeight(mScroller.getCurrY());
	// } else {
	// mFooterView.setBottomMargin(mScroller.getCurrY());
	// }
	// postInvalidate();
	// invokeOnScrolling();
	// }
	// super.computeScroll();
	// }
	//
	// @Override
	// public void setOnScrollListener(OnScrollListener l) {
	// mScrollListener = l;
	// }
	//
	// public void onScrollStateChanged(AbsListView view, int scrollState) {
	// if (mScrollListener != null) {
	// mScrollListener.onScrollStateChanged(view, scrollState);
	// }
	// }
	//
	// public void onScroll(AbsListView view, int firstVisibleItem,
	// int visibleItemCount, int totalItemCount) {
	// // send to user's listener
	// mTotalItemCount = totalItemCount;
	// if (mScrollListener != null) {
	// mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
	// totalItemCount);
	// }
	// }
	//
	// public void setXListViewListener(IXListViewListener l) {
	// mListViewListener = l;
	// }
	//
	// /**
	// * you can listen ListView.OnScrollListener or this one. it will invoke
	// * onXScrolling when header/footer scroll back.
	// */
	// public interface OnXScrollListener extends OnScrollListener {
	// public void onXScrolling(View view);
	// }
	//
	// /**
	// * implements this interface to get refresh/load more event.
	// */
	// public interface IXListViewListener {
	// public void onRefresh();
	// public void onLoadMore();
	// }
	//
	// public void removeFooterView(){
	// mFooterView.hide();
	// }
	//
	// public void getFooterView(){
	// mFooterView.show();
	// }

}