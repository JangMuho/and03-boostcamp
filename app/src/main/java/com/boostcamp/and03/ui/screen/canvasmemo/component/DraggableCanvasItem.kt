package com.boostcamp.and03.ui.screen.canvasmemo.component

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

@Composable
fun DraggableCanvasItem(
    nodeId: String,
    worldOffset: Offset,
    onMove: (Offset) -> Unit,
    onSizeChanged: (IntSize) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
    onClick: ((String) -> Unit)? = null,
    draggable: Boolean = true,
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                translationX = worldOffset.x
                translationY = worldOffset.y
            }
            .onGloballyPositioned { coords ->
                onSizeChanged(coords.size)
            }
            .pointerInput(
                nodeId,   // nodeId가 바뀌거나
                draggable // draggable이 바뀌면 pointerInput 블록 재시작
            ) {
                awaitEachGesture { // 한 번의 제스처(포인터 down ~ up) 단위로 처리
                    val slop = viewConfiguration.touchSlop // 드래그가 아니라 탭으로 인식하는 임계값
                    var dragged = false

                    var totalDragOffset = Offset.Zero

                    while (true) {
                        val event = awaitPointerEvent()     // 다음 포인터 이벤트를 suspend로 기다렸다가 받음
                        val change = event.changes.first()  // 변화한 목록 중 첫 번째 포인터만 가져와 처리

                        if (!change.pressed) break          // 포인터가 눌려있지 않으면 루프 종료

                        val delta = change.positionChange() // 포인터의 위치 변화량 계산
                        totalDragOffset += delta            // 이동량 누적

                        if (!dragged && totalDragOffset.getDistance() > slop) {
                            dragged = true
                        }

                        if (dragged && draggable) {
                            change.consume()
                            onMove(delta)
                        }
                    }

                    if (!dragged) {
                        onClick?.invoke(nodeId)
                    }
                }
            }
    ) {
        content()
    }
}

//@Composable
//fun DraggableCanvasItem(
//    nodeId: String,
//    worldOffset: Offset,
//    onMove: (Offset) -> Unit,
//    onSizeChanged: (IntSize) -> Unit,
//    modifier: Modifier = Modifier,
//    content: @Composable BoxScope.() -> Unit,
//    onClick: ((String) -> Unit)? = null,
//    draggable: Boolean = true,
//) {
//    Box(
//        modifier = modifier
//            .graphicsLayer {
//                translationX = worldOffset.x
//                translationY = worldOffset.y
//            }
//            .onGloballyPositioned { coords ->
//                onSizeChanged(coords.size)
//            }
//            .then(
//                if (onClick != null) {
//                    Modifier.pointerInput(nodeId) {
//                        detectTapGestures(
//                            onPress = {
//                                Log.d("TAP", "onPress")
//                                tryAwaitRelease()
//                            },
//                            onTap = {
//                                Log.d("TAP", "onTap")
//                                onClick(nodeId)
//                            }
//                        )
//                    }
//                } else {
//                    Modifier
//                }
//            )
//            .then(
//                if (draggable) {
//                    Modifier.pointerInput(nodeId) {
//                        detectDragGestures(
//                            onDragStart = {
//                                Log.d("DRAG", "onDragStart for nodeId=$nodeId")
//                            },
//                            onDrag = { change, dragAmount ->
//                                Log.d("DRAG", "onDrag nodeId=$nodeId, dragAmount=$dragAmount")
//                                Log.d("DRAG", "positionChange=${change.positionChange()}")
//                                change.consume()
//                                onMove(dragAmount)
//                            },
//                            onDragEnd = {
//                                Log.d("DRAG", "onDragEnd for nodeId=$nodeId")
//                            },
//                            onDragCancel = {
//                                Log.d("DRAG", "onDragCancel for nodeId=$nodeId")
//                            }
//                        )
//                    }
//                } else Modifier
//            )
//    ) {
//        content()
//    }
//}

//@Composable
//fun DraggableCanvasItem(
//    nodeId: String,
//    worldOffset: Offset,
//    onMove: (Offset) -> Unit,
//    onSizeChanged: (IntSize) -> Unit,
//    modifier: Modifier = Modifier,
//    content: @Composable BoxScope.() -> Unit,
//    onClick: ((String) -> Unit)? = null,
//    draggable: Boolean = true,
//) {
//    Box(
//        modifier = modifier
//            .graphicsLayer {
//                translationX = worldOffset.x
//                translationY = worldOffset.y
//            }
//            .onGloballyPositioned { coords ->
//                onSizeChanged(coords.size)
//            }
//            .then(
//                if (onClick != null) {
//                    Modifier.pointerInput(nodeId, onClick) {
//                        detectTapGestures(
//                            onTap = {
//                                onClick(nodeId)
//                            }
//                        )
//                    }
//                } else {
//                    Modifier
//                }
//            )
//            .then(
//                if (draggable) {
//                    Modifier.pointerInput(nodeId, draggable) {
//                        detectDragGestures { change, dragAmount ->
//                            change.consume()
//                            onMove(dragAmount)
//                        }
//                    }
//                } else Modifier
//            )
//    ) {
//        content()
//    }
//}