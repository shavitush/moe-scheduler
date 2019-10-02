/*
 * Copyright (C) 2019, y785, http://github.com/y785
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package moe.maple.scheduler.tasks.delay;

import moe.maple.scheduler.Task;

public class DelayedTask implements Task {

    private final Task actual;
    private final long start, delay;

    private boolean hasRun;

    public DelayedTask(Task actual, long delay, long start) {
        if (actual == null)
            throw new IllegalArgumentException("Delayed task is set to null.");
        this.actual = actual;
        this.delay = delay;
        this.start = start;
    }

    public DelayedTask(Task actual, long delay) {
        this(actual, delay, System.currentTimeMillis());
    }

    @Override
    public boolean isEventAsync() {
        return actual.isEventAsync();
    }

    @Override
    public boolean isEventDone() {
        return hasRun;
    }

    @Override
    public void update(long delta) {
        if (!hasRun && delta - start >= delay) {
            actual.update(delta);
            hasRun = true;
        }
    }
}
