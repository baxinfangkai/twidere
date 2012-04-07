/*
 *              Copyright (C) 2011 The MusicMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mariotaku.twidere.activity;

import org.mariotaku.twidere.Constants;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class LicenseActivity extends SherlockFragmentActivity implements Constants {

	private Uri mUri = Uri.parse("file:///android_asset/gpl-3.0-standalone.html");

	private WebView mWebView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mWebView = new WebView(this);
		setContentView(mWebView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		mWebView.loadUrl(mUri.toString());
		mWebView.setWebViewClient(new LicenseWebViewClient());
		mWebView.getSettings().setBuiltInZoomControls(true);

	}

	@Override
	public void onDestroy() {
		mWebView.clearCache(true);
		super.onDestroy();
	}

	private class LicenseWebViewClient extends WebViewClient {

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			setTitle(view.getTitle());
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Uri uri = Uri.parse(url);
			if (uri.getScheme().equals(mUri.getScheme())) return false;

			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(intent);
			return true;
		}
	}
}